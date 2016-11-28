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

import com.io7m.jaffirm.core.Preconditions;
import com.io7m.jcoords.core.ImmutableStyleType;
import com.io7m.jtensors.Matrix3x3DType;
import com.io7m.jtensors.MatrixHeapArrayM3x3D;
import com.io7m.jtensors.VectorI3D;
import org.immutables.value.Value;

/**
 * An arrangement of orthonormal axes forming a Cartesian coordinate system.
 */

@Value.Immutable
@ImmutableStyleType
public interface CAxisSystemType
{
  /**
   * @return The axis facing right
   */

  @Value.Parameter
  CAxis right();

  /**
   * @return The axis facing up
   */

  @Value.Parameter
  CAxis up();

  /**
   * @return The axis facing forward
   */

  @Value.Parameter
  CAxis forward();

  /**
   * Check preconditions for the type.
   */

  @Value.Check
  default void checkPreconditions()
  {
    Preconditions.checkPrecondition(
      this.right().axis() != this.up().axis(),
      "Right and up axis must be perpendicular");
    Preconditions.checkPrecondition(
      this.right().axis() != this.forward().axis(),
      "Right and forward axis must be perpendicular");
    Preconditions.checkPrecondition(
      this.up().axis() != this.forward().axis(),
      "Up and forward axis must be perpendicular");
  }

  /**
   * @return The basis matrix for the axis system
   */

  default Matrix3x3DType basis()
  {
    final CAxis r = this.right();
    final CAxis u = this.up();
    final CAxis f = this.forward();

    final VectorI3D column_x;
    if (r.axis() == 'x') {
      column_x = r.vector();
    } else if (u.axis() == 'x') {
      column_x = u.vector();
    } else {
      column_x = f.vector();
    }

    final VectorI3D column_y;
    if (r.axis() == 'y') {
      column_y = r.vector();
    } else if (u.axis() == 'y') {
      column_y = u.vector();
    } else {
      column_y = f.vector();
    }

    final VectorI3D column_z;
    if (r.axis() == 'z') {
      column_z = r.vector();
    } else if (u.axis() == 'z') {
      column_z = u.vector();
    } else {
      column_z = f.vector();
    }

    final Matrix3x3DType m = MatrixHeapArrayM3x3D.newMatrix();
    m.setR0C0D(column_x.getXD());
    m.setR1C0D(column_x.getYD());
    m.setR2C0D(column_x.getZD());

    m.setR0C1D(column_y.getXD());
    m.setR1C1D(column_y.getYD());
    m.setR2C1D(column_y.getZD());

    m.setR0C2D(column_z.getXD());
    m.setR1C2D(column_z.getYD());
    m.setR2C2D(column_z.getZD());
    return m;
  }
}
