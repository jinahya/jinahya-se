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
package com.github.jinahya.lang.reflect;

import com.github.jinahya.lang.IntFieldEnum;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class ModifierTest {

    @Test
    public static void assertUniqueFieldValues() {

        final int[] array = IntFieldEnum.fieldValues(Modifier.class);
        final Set<Integer> set
                = Arrays.stream(array).boxed().collect(Collectors.toSet());
        assertEquals(set.size(), Modifier.values().length);
    }

}
