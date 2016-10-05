/*
 * Created by Gigigo Android Team
 *
 * Copyright (C) 2016 Gigigo Mobile Services SL
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

package com.gigigo.gggjavalib.general.testing.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import java.util.Date;

public class IsDateEqualTo extends BaseMatcher<Date> {

    private final Date expectedValue;

    public IsDateEqualTo(Date expectedValue) {
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean matches(Object item) {
        boolean areEquals = Math.abs(expectedValue.getTime() - ((Date) item).getTime()) < 1000;
        return areEquals;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(expectedValue.toString());
    }

    @Factory
    public static Matcher<Date>
    isDateEqualTo(Date t) {
        return new IsDateEqualTo(t);
    }
}
