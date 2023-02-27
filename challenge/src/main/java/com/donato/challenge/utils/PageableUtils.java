package com.donato.challenge.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {

	
	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @param sortDirection
	 * @return
	 */
	public static Pageable buildPageable(int pageNo, int pageSize, String sortField, String sortDirection) {
	
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable= PageRequest.of(pageNo -1 , pageSize, sort);
		return pageable;
	}
}
