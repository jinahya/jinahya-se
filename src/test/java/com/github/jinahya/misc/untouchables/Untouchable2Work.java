/*
 * Copyright 2014 Jin Kwon <onacit at gmail.com>.
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


package com.github.jinahya.misc.untouchables;


import com.github.jinahya.misc.Dangerous;
import sun.misc.Unsafe;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class Untouchable2Work {


    public static void main(final String[] args) throws InstantiationException {

        final Unsafe unsafe = Dangerous.theUnsafeInstance();
        final Untouchable2 untouchable2
            = (Untouchable2) unsafe.allocateInstance(Untouchable2.class);
        untouchable2.secret();
    }


}

