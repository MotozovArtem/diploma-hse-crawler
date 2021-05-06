package ru.hse.diploma.controller;

import static java.util.Objects.requireNonNull;

import ru.hse.diploma.domain.WebPageAnalyseResult;
import ru.hse.diploma.repository.WebPageAnalyseResultRepository;
import ru.hse.diploma.util.ConverterService;
import ru.hse.diploma.view.WebPageAnalyseResultViewItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * todo armotozov
 *
 * @author Artem Motozov
 * @since 2021.05.06
 */
@RestController
@RequestMapping("/api/web_page_analyse_result_view")
public class WebPageAnalyseResultViewItemController {

	private final WebPageAnalyseResultRepository webPageAnalyseResultRepository;

	private final ConverterService<WebPageAnalyseResult, WebPageAnalyseResultViewItem> converterService;

	public WebPageAnalyseResultViewItemController(WebPageAnalyseResultRepository webPageAnalyseResultRepository, ConverterService<WebPageAnalyseResult, WebPageAnalyseResultViewItem> converterService) {
		this.webPageAnalyseResultRepository = webPageAnalyseResultRepository;
		this.converterService = converterService;
	}

	/**
	 * todo armotozov.
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<WebPageAnalyseResultViewItem> getWebPageViewItem(@PathVariable("id") String id) {
		requireNonNull(id);
		Optional<WebPageAnalyseResult> analyseResultOptional = webPageAnalyseResultRepository.findById(id);
		return analyseResultOptional.map(analyseResult -> new ResponseEntity<>(converterService.toViewItem(analyseResult), HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
	}

	/**
	 * todo armotozov.
	 *
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<WebPageAnalyseResultViewItem> getAllWebPageViewItems() {
		return webPageAnalyseResultRepository.findAll()
				.stream()
				.map(converterService::toViewItem)
				.collect(Collectors.toList());
	}
}
