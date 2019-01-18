package com.mojito.mojitoboot.common.utils.fileRenderUtil;

import com.google.common.collect.Maps;

import java.util.Map;

public class FileRenderFactory {

    private static Map<FileType, FileRender> fileRenderMap = Maps.newHashMap();

    public static void register(FileType fileType,FileRender fileRender){
        fileRenderMap.put(fileType,fileRender);
    }

    public static FileRender getFileRender(FileType fileType){
        return fileRenderMap.get(fileType);
    }

}
