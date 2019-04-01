package com.example.craily.utils;

import java.io.Serializable;
import java.util.UUID;

public class UUIDUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 771586671057435688L;

	/**
	 * 获取32位UUID
	 * @return
	 */
	public static String get32UUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
