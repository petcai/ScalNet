/*
 *
 *  * Copyright 2016 Skymind,Inc.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package org.deeplearning4j.scalnet.layers.reshaping

import org.deeplearning4j.nn.conf.InputPreProcessor
import org.deeplearning4j.nn.conf.preprocessor.CnnToFeedForwardPreProcessor
import org.deeplearning4j.scalnet.layers.Node
import org.deeplearning4j.scalnet.layers.Preprocessor


/**
  * Flattens structured image-like inputs into vector. Input should have
  * three dimensions: height (number of rows), width (number of columns),
  * and number of channels.
  *
  * @author David Kale
  */
class Flatten3D(nIn: Int = 0) extends Node with Preprocessor {
  inputShape = List(nIn)

  override def outputShape = List(inputShape.product)

  override def compile: InputPreProcessor = {
    if (inputShape.length != 3)
      throw new IllegalArgumentException("Input shape must be length 3.")

    new CnnToFeedForwardPreProcessor(inputShape.head, inputShape.tail.head, inputShape.last)
  }
}
