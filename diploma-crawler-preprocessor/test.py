from domain.Database import Database
from domain.Domain import WebPage, WebPageAnalyseResult, ANALYSE_PHASE
from bs4 import BeautifulSoup, Tag
from nltk.stem import WordNetLemmatizer
import nltk
import logging
import datetime
import json
import traceback

DB = Database()

LOG = logging.getLogger(__name__)
LOG.setLevel(logging.DEBUG)
LOG.propagate = False

LOG_FORMATTER = logging.Formatter(
    "%(asctime)s %(name)s [%(threadName)s]: %(levelname)s %(message)s")

FILE_LOGGING_HANDLER = logging.FileHandler("trace.log")
FILE_LOGGING_HANDLER.setLevel(logging.DEBUG)
FILE_LOGGING_HANDLER.setFormatter(LOG_FORMATTER)

CONSOLE_LOGGING_HANDLER = logging.StreamHandler()
CONSOLE_LOGGING_HANDLER.setLevel(logging.DEBUG)
CONSOLE_LOGGING_HANDLER.setFormatter(LOG_FORMATTER)

LOG.addHandler(FILE_LOGGING_HANDLER)
LOG.addHandler(CONSOLE_LOGGING_HANDLER)

lemmatizer = WordNetLemmatizer()


def preprocess_webpage(web_page_id: str) -> None:
    LOG.info(
        "Started preprocessing for WebPage with id: %s", web_page_id)
    web_page = WebPage.objects.get(pk=web_page_id)
    if web_page is not None:
        LOG.info(
            "Loaded from db WebPage object with id: %s", web_page_id)
        try:
            web_page_analyse_result = WebPageAnalyseResult.objects.get(
                web_page=web_page)
            LOG.info("Loaded from db WebPageAnalyseResult with id: %s for WebPage with id: %s",
                     web_page_analyse_result._DomainObject__id, web_page_id)
        except Exception as e:
            LOG.debug(
                "Catched exception while getting WebPageAnalyseResult! Exception: %s", e)
            web_page_analyse_result = WebPageAnalyseResult()
            LOG.info(
                "Created WebPageAnalyseResult for WebPage (id: %s)", web_page_id)
        web_page_analyse_result.start_analyse = datetime.datetime.utcnow()
        web_page_analyse_result.phase = "ANALYSE_START"
        web_page_analyse_result.web_page = web_page
        web_page_analyse_result.save()

        try:
            preprocess(web_page_analyse_result)
        except Exception as e:
            LOG.info("Exception while preprocessing web page text: %s", e)
            LOG.exception("Traceback: %s", e)
            web_page_analyse_result.phase = "ANALYSE_FAILED"
            web_page_analyse_result.error_text = str(e)
            web_page_analyse_result.save()
    else:
        LOG.warning(
            "WebPage with given id: (%s) not exist!", web_page_id)


def preprocess(web_page_analyse_result: WebPageAnalyseResult):
    # очистка текста от HTML тегов
    raw_text_as_list = delete_html_text(web_page.page_text)
    web_page_analyse_result.raw_text = '\n'.join(raw_text_as_list)
    web_page_analyse_result.phase = "HTML_CLEANING"
    web_page_analyse_result.save()



    # Токенизация на предложения и слова
    LOG.info("Started sentence tokenization. WebPageAnalyseResult (id: %s)",
             web_page_analyse_result._DomainObject__id)
    sentence_tokenize = list()
    for paragraph in raw_text_as_list:
        sentence_tokenize.append(tokenize_in_sentence(paragraph))

    LOG.info("Word sentence tokenization. WebPageAnalyseResult (id: %s)",
             web_page_analyse_result._DomainObject__id)
    word_tokenize = list()
    for sentences in sentence_tokenize:
        for sentence in sentences:
            word_tokenize.append(tokenize_in_word(sentence))
    result = {
        "sentence_tokenize": json.dumps({k: v for k, v in zip(range(len(sentence_tokenize)), sentence_tokenize)}),
        "word_tokenize": json.dumps({k: v for k, v in zip(range(len(word_tokenize)), word_tokenize)})
    }
    web_page_analyse_result.tokenization_result = json.dumps(result)
    web_page_analyse_result.phase = "TOKENIZATION"
    web_page_analyse_result.save()

    # Лемматизация
    LOG.info("Started lemmatization. WebPageAnalyseResult (id: %s)",
             web_page_analyse_result._DomainObject__id)
    lemmatization_result = lemmatization(word_tokenize)
    web_page_analyse_result.lemmatization_result = json.dumps(
        lemmatization_result)
    web_page_analyse_result.phase = "LEMMATIZATION"
    web_page_analyse_result.save()

    # Нормализация
    LOG.info("Started normalization. WebPageAnalyseResult (id: %s)",
             web_page_analyse_result._DomainObject__id)
    normaliztion_result = normalization(sentence_tokenize)
    web_page_analyse_result.normalization_result = json.dumps(
        normaliztion_result)
    web_page_analyse_result.phase = "NORMALIZATION"
    web_page_analyse_result.save()

    # Анализ количества слов
    LOG.info("Started word count analyse. WebPageAnalyseResult (id: %s)",
             web_page_analyse_result._DomainObject__id)
    word_count_result = analyse_word_count(word_tokenize)
    web_page_analyse_result.word_count = json.dumps(
        word_count_result)
    web_page_analyse_result.phase = "WORD_COUNTING"
    web_page_analyse_result.save()

    # Завершение предобработки
    LOG.info("Finishing analyse. WebPageAnalyseResult (id: %s)",
             web_page_analyse_result._DomainObject__id)
    web_page_analyse_result.phase = "ANALYSE_FINISHED"
    web_page_analyse_result.finish_analyse = datetime.datetime.utcnow()
    web_page_analyse_result.save()


def delete_html_text(page_text: str) -> list:
    soup = BeautifulSoup(page_text, 'lxml')
    text_from_elements = list()
    for child_element in soup.body.children:
        text_from_elements.extend(get_text_from_element(child_element))
    text_from_elements = list(filter(
        lambda el: not (el.isspace() or len(el) == 0), text_from_elements))
    text_from_elements = list(map(lambda el: el.strip(), text_from_elements))
    return text_from_elements


def get_text_from_element(html_element) -> list:
    result_list = list()
    if type(html_element) is Tag:
        result_list.append(html_element.get_text())
        for child in html_element.children:
            result_list.extend(get_text_from_element(child))
    return result_list


def tokenize_in_sentence(text: str) -> list:
    return nltk.sent_tokenize(text)


def tokenize_in_word(sentence: str) -> list:
    return nltk.word_tokenize(sentence)


def lemmatization(word_tokenize_in_sentences, pos=None) -> dict:
    lemmatization_result = dict()
    for words in word_tokenize_in_sentences:
        for word in words:
            if pos is None:
                lemmatization_result[word] = lemmatizer.lemmatize(word)
            else:
                lemmatization_result[word] = lemmatizer.lemmatize(
                    word, pos=pos)
    return lemmatization_result


def normalization(sentence_list: list) -> list:
    normalization_result = list()
    for sentences in sentence_list:
        for sentence in sentences:
            normalization_result.append(normalize_sentence(sentence))
    return normalization_result


def normalize_sentence(sentence: str) -> str:
    normalized = sentence
    while "  " in normalized:
        normalized = normalized.replace("  ", " ")
    while "\n" in normalized:
        normalized = normalized.replace('\n', " ")
    symbols_to_replace = u"-_=!?.,:;\"'=+«»"
    for symb in symbols_to_replace:
        normalized = normalized.replace(symb, " ")
    return normalized.lower()


def analyse_word_count(word_tokenize: list) -> dict:
    result = {}
    word_count = {}
    for words in word_tokenize:
        for word in words:
            count = word_count.setdefault(word, 0)
            word_count[word] = count + 1
    result['word_counts'] = word_count
    result['unique_words'] = len(word_count.keys())
    return result


if __name__ == '__main__':
    for web_page in WebPage.objects:
        preprocess_webpage(str(web_page._DomainObject__id))
