package ru.hse.diploma.controller;

import static java.util.Objects.requireNonNull;

import ru.hse.diploma.domain.WebPage;
import ru.hse.diploma.repository.WebPageRepository;
import ru.hse.diploma.util.ConverterService;
import ru.hse.diploma.view.WebPageViewItem;
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
@RequestMapping("/api/web_page_view")
public class WebPageViewItemController {

	/**
	 * todo armotozov.
	 */
	private final WebPageRepository webPageRepository;

	/**
	 * todo armotozov.
	 */
	private final ConverterService<WebPage, WebPageViewItem> converterService;

	/**
	 * Конструктор.
	 *
	 * @param webPageRepository
	 * @param converterService
	 */
	public WebPageViewItemController(WebPageRepository webPageRepository, ConverterService<WebPage, WebPageViewItem> converterService) {
		this.webPageRepository = webPageRepository;
		this.converterService = converterService;
	}

	/**
	 * todo armotozov.
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<WebPageViewItem> getWebPageViewItem(@PathVariable("id") String id) {
		requireNonNull(id);
		Optional<WebPage> webPageOptional = webPageRepository.findById(id);
		return webPageOptional.map(webPage -> new ResponseEntity<>(converterService.toViewItem(webPage), HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
	}

	/**
	 * todo armotozov.
	 *
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<WebPageViewItem> getAllWebPageViewItems() {
		return webPageRepository.findAll()
				.stream()
				.map(converterService::toViewItem)
				.collect(Collectors.toList());
	}
}
