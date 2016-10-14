/*
 * Copyright 2016 Manuel Rebollo Báez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrebollob.loteriadenavidad

import spock.lang.Specification;

class CollectionsUtilTests extends Specification {

    def "Check size of result array"() {
        when:
        def array = CollectionsUtil.toArray(items)

        then:
        array.length == expected

        where:
        items           | expected
        ['a', 'b', 'c'] | 3
        ['a', 'b']      | 2
        ['a']           | 1
        []              | 0
    }

    def "Check content of result"() {
        when:
        def array = CollectionsUtil.toArray(items)

        then:
        array == expected

        where:

        items           | expected
        ['a', 'b', 'c'] | ['a', 'b', 'c']
        [1, 2, 3]       | [1, 2, 3]
        []              | []

    }
}