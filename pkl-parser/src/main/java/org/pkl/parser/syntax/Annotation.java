/*
 * Copyright © 2025 Apple Inc. and the Pkl project authors. All rights reserved.
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
package org.pkl.parser.syntax;

import java.util.List;
import org.pkl.parser.ParserVisitor;
import org.pkl.parser.Span;
import org.pkl.parser.util.Nullable;

public class Annotation extends AbstractNode {
  public Annotation(List<Node> nodes, Span span) {
    super(span, nodes);
  }

  @Override
  public <T> @Nullable T accept(ParserVisitor<? extends T> visitor) {
    return visitor.visitAnnotation(this);
  }

  public Type getType() {
    assert children != null;
    return (Type) children.get(0);
  }

  public @Nullable ObjectBody getBody() {
    assert children != null;
    return (ObjectBody) children.get(1);
  }
}
