/*
 * Copyright 2013 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
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


package com.github.jinahya.xml.bind.test.map;


import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
@XmlTransient
public abstract class AbstractDepartment {


    public Map<Long, Employee> getEmployees() {

        if (employees == null) {
            employees = new HashMap<Long, Employee>();
        }

        return employees;
    }


    public void setEmployees(final Map<Long, Employee> employees) {

        this.employees = employees;
    }


    @Override
    public int hashCode() {

        int hash = 5;
        hash = 97 * hash + (this.employees != null
                            ? this.employees.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractDepartment other = (AbstractDepartment) obj;
        if (this.employees != other.employees
            && (this.employees == null
                || !this.employees.equals(other.employees))) {
            return false;
        }
        return true;
    }


    private Map<Long, Employee> employees;

}

