package com.jw.maoblog.service;

import com.jw.maoblog.domain.Catalog;
import com.jw.maoblog.domain.User;

import java.util.List;

/**
 * Catalog Service interface.
 */
public interface CatalogService {
	/**
	 * save Catalog
	 * @param catalog
	 * @return
	 */
	Catalog saveCatalog(Catalog catalog);
	
	/**
	 * delete Catalog
	 * @param id
	 * @return
	 */
	void removeCatalog(Long id);

	/**
	 * get Catalog by catalogId
	 * @param id
	 * @return
	 */
	Catalog getCatalogById(Long id);
	
	/**
	 * get the list of Catalogs belong to the given User.
	 * @return
	 */
	List<Catalog> listCatalogs(User user);
}
