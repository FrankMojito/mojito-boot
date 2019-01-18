package com.mojito.mojitoboot.common.utils.fileRenderUtil;

import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public interface FileRender<T> extends InitializingBean {

    public T fileRender(List<Object> object,String... condition);

}
