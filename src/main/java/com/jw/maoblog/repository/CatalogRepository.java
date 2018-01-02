package com.jw.maoblog.repository;

import com.jw.maoblog.domain.Catalog;
import com.jw.maoblog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Catalog Repository interface
 */
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
	
	/**
	 * return the catalogs belong to  one specific user.
	 * @param user
	 * @return
	 */
	List<Catalog> findByUser(User user);
	
	/**
	 * return the catalog based ong the user and the name of that catalog.
	 * @param user
	 * @param name
	 * @return
	 */
	List<Catalog> findByUserAndName(User user,String name);
}