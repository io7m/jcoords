/*
 * Copyright © 2016 <code@io7m.com> http://io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.jcoords.core.conversion;

import com.io7m.jnull.NullCheck;
import com.io7m.jtensors.VectorI3D;

/**
 * An axis.
 */

public enum CAxis
{
  /**
   * The positive X axis.
   */

  AXIS_POSITIVE_X('x', new VectorI3D(1.0, 0.0, 0.0)),

  /**
   * The positive Y axis.
   */

  AXIS_POSITIVE_Y('y', new VectorI3D(0.0, 1.0, 0.0)),

  /**
   * The positive Z axis.
   */

  AXIS_POSITIVE_Z('z', new VectorI3D(0.0, 0.0, 1.0)),

  /**
   * The negative X axis.
   */

  AXIS_NEGATIVE_X('x', new VectorI3D(-1.0, 0.0, 0.0)),

  /**
   * The negative Y axis.
   */

  AXIS_NEGATIVE_Y('y', new VectorI3D(0.0, -1.0, 0.0)),

  /**
   * The negative Z axis.
   */

  AXIS_NEGATIVE_Z('z', new VectorI3D(0.0, 0.0, -1.0));

  private final int name;
  private final VectorI3D vector;

  CAxis(
    final int in_name,
    final VectorI3D in_v)
  {
    this.name = in_name;
    this.vector = NullCheck.notNull(in_v, "Vector");
  }

  /**
   * @return The axis as 'x', 'y' or 'z'
   */

  public int axis()
  {
    return this.name;
  }

  /**
   * @return The vector for the axis
   */

  public VectorI3D vector()
  {
    return this.vector;
  }
}
