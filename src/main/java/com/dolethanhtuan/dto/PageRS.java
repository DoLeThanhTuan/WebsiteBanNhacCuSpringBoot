package com.dolethanhtuan.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PageRS {
	private List<ProductDTO> listProDTO;
	private long totalElement;
	private int totalPage;
	private int size;
	private int index;
}
