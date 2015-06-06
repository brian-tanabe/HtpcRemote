package com.bttanabe2.remote.test.unit;

import com.btanabe2.remote.directory.MediaFileFinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by brian on 6/5/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/spring-configuration/unit-tests.xml")
public class MediaFileFinderTests {
    private static final String SINGLE_FILE_TEST_DIRECTORY = "./src/test/resources/media-test-directory/child-directory-1";
    private static final String DIRECTORY_WITH_CHILD_DIRECTORIES_TEST_DIRECTORY = "./src/test/resources/media-test-directory";
    private static final String DIRECTORY_CONTAINING_IGNORED_FILE_TYPES = "./src/test/resources/media-test-directory/child-directory-files-with-ignored-extensions";

    @Autowired
    private MediaFileFinder mediaFinder;

    @Test
    public void shouldBeAbleToFindSingleFilesInTheBaseDirectory() {
        List<String> mediaFilesFound = mediaFinder.findMediaFilesInPath(new File(SINGLE_FILE_TEST_DIRECTORY));

        assertEquals(1, mediaFilesFound.size());
        assertTrue(mediaFilesFound.get(0).endsWith("video-file-in-child-directory-1.mkv"));
    }

    @Test
    public void shouldBeAbleToFindFilesInChildDirectories() {
        List<String> mediaFilesFound = mediaFinder.findMediaFilesInPath(new File(DIRECTORY_WITH_CHILD_DIRECTORIES_TEST_DIRECTORY));

        assertEquals(2, mediaFilesFound.size());
    }

    @Test
    public void shouldBeAbleToIgnoreFilesWithDbExtension() {
        List<String> mediaFilesFound = mediaFinder.findMediaFilesInPath(new File(DIRECTORY_CONTAINING_IGNORED_FILE_TYPES));

        assertEquals(0, mediaFilesFound.size());
    }
}
