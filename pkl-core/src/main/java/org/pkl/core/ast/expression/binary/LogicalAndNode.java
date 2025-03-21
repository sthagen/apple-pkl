/*
 * Copyright © 2024 Apple Inc. and the Pkl project authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pkl.core.ast.expression.binary;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.oracle.truffle.api.source.SourceSection;
import org.pkl.core.ast.ExpressionNode;

@NodeInfo(shortName = "&&")
public abstract class LogicalAndNode extends ShortCircuitingExpressionNode {
  protected LogicalAndNode(SourceSection sourceSection, ExpressionNode rightNode) {
    super(sourceSection, rightNode);
  }

  @Specialization
  protected boolean eval(VirtualFrame frame, boolean left) {
    try {
      return left && rightNode.executeBoolean(frame);
    } catch (UnexpectedResultException e) {
      throw operatorNotDefined(true, e.getResult());
    }
  }
}
