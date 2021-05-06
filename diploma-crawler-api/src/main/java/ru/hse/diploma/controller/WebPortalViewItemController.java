package ru.hse.diploma.controller;

import static java.util.Objects.requireNonNull;

import ru.hse.diploma.domain.WebPortal;
import ru.hse.diploma.repository.WebPortalRepository;
import ru.hse.diploma.util.ConverterService;
import ru.hse.diploma.view.WebPortalViewItem;
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
@RequestMapping("/api/web_portal_view")
public class WebPortalViewItemController {

	/**
	 * todo armotozov.
	 */
	private final WebPortalRepository webPortalRepository;

	private final ConverterService<WebPortal, WebPortalViewItem> converterService;

	public WebPortalViewItemController(WebPortalRepository webPortalRepository, ConverterService<WebPortal, WebPortalViewItem> converterService) {
		this.webPortalRepository = webPortalRepository;
		this.converterService = converterService;
	}

	/**
	 * todo armotozov.
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<WebPortalViewItem> getWebPageViewItem(@PathVariable("id") String id) {
		requireNonNull(id);
		Optional<WebPortal> webPortalOptional = webPortalRepository.findById(id);
		return webPortalOptional.map(webPortal -> new ResponseEntity<>(converterService.toViewItem(webPortal), HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(null, HttpStatus.OK));
	}

	/**
	 * todo armotozov.
	 *
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<WebPortalViewItem> getAllWebPageViewItems() {
		return webPortalRepository.findAll()
				.stream()
				.map(converterService::toViewItem)
				.collect(Collectors.toList());
	}
}
