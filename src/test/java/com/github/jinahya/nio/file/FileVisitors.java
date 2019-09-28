/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jinahya.nio.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public final class FileVisitors {

    /**
     * @return
     * @see <a href="http://goo.gl/TtuKwD">JAVA 7+: DELETING RECURSIVELY A
     * DIRECTORY</a>
     */
    public static FileVisitor<Path> pathCleaner() {

        return new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(final Path file,
                                             final BasicFileAttributes attrs)
                    throws IOException {

                Files.delete(file);

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(final Path dir,
                                                      final IOException exc)
                    throws IOException {

                Files.delete(dir);

                return FileVisitResult.CONTINUE;
            }
        };
    }

    private FileVisitors() {

        super();
    }
}
