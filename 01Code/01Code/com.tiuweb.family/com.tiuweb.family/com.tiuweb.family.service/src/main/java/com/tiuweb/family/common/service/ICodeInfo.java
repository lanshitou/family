package com.tiuweb.family.common.service;

import java.util.List;

public interface ICodeInfo<T> {

	List<T> getCodeInfoList(final String codeType);

	List<T> getCodeInfo(final String codeType, String codeValue);
}
