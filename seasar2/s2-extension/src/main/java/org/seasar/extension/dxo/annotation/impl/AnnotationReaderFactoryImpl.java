/*
 * Copyright 2004-2015 the Seasar Foundation and the Others.
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
package org.seasar.extension.dxo.annotation.impl;

import java.lang.reflect.Constructor;

import org.seasar.extension.dxo.annotation.AnnotationReader;
import org.seasar.extension.dxo.annotation.AnnotationReaderFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.exception.ClassNotFoundRuntimeException;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.ConstructorUtil;

/**
 * 実行環境で利用可能な{@link AnnotationReader}のインスタンスを作成するファクトリの実装クラスです。
 * <p>
 * Java5以上が利用可能な環境では{@link org.seasar.extension.dxo.annotation.impl.TigerAnnotationReader}のインスタンスを返します。
 * そうでない場合は{@link org.seasar.extension.dxo.annotation.impl.ConstantAnnotationReader}を返します。
 * </p>
 * 
 * @author Satoshi Kimura
 * @author koichik
 */
public class AnnotationReaderFactoryImpl implements AnnotationReaderFactory {

    /** {@link org.seasar.extension.dxo.annotation.impl.TigerAnnotationReader}のFQN名 */
    protected static final String TIGER_ANNOTATION_HANDLER_CLASS_NAME = "org.seasar.extension.dxo.annotation.impl.TigerAnnotationReader";

    /** S2コンテナ */
    protected S2Container container;

    /** 実行環境で利用可能な{@link AnnotationReader} */
    protected AnnotationReader annotationReader;

    /**
     * インスタンスを構築します。
     * 
     * @param container
     *            S2コンテナ
     */
    public AnnotationReaderFactoryImpl(final S2Container container) {
        this.container = container;
        annotationReader = new ConstantAnnotationReader(container);
        try {
            final Class clazz = ClassUtil
                    .forName(TIGER_ANNOTATION_HANDLER_CLASS_NAME);
            final Constructor ctor = ClassUtil.getConstructor(clazz,
                    new Class[] { S2Container.class, AnnotationReader.class });
            annotationReader = (AnnotationReader) ConstructorUtil.newInstance(
                    ctor, new Object[] { container, annotationReader });
        } catch (final ClassNotFoundRuntimeException ignore) {
        }
    }

    public AnnotationReader getAnnotationReader() {
        return annotationReader;
    }

}
