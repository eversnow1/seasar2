/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.framework.ejb.unit.impl;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hoge4 {
	private Long aaa;

	private java.util.Date ccc;

    public Hoge4(){
    }
    
	@Id
	public Long getAaa() {
		return aaa;
	}

	public void setAaa(Long aaa) {
		this.aaa = aaa;
	}

	public void getBbb() {
	}

	public java.util.Date getCcc() {
		return ccc;
	}
}
