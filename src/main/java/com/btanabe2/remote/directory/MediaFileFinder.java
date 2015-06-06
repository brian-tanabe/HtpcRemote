package com.btanabe2.remote.directory;

import org.apache.commons.io.FileUtils;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by brian on 6/5/15.
 */
@ContextConfiguration("classpath*:/spring-configuration/application.xml")
public class MediaFileFinder {

    private Set<String> extensionsToSearchFor;

    public List<String> findMediaFilesInPath(File baseDirectory) {
        List<String> allFilesInBaseDirectory = new ArrayList<>();
        FileUtils.listFiles(baseDirectory, extensionsToSearchFor.toArray(new String[extensionsToSearchFor.size()]), true).forEach(f -> allFilesInBaseDirectory.add(f.getAbsolutePath()));

        return allFilesInBaseDirectory;
    }

    public void setExtensionsToSearchFor(Set<String> extensionsToSearchFor) {
        this.extensionsToSearchFor = extensionsToSearchFor;
    }
}
