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

import com.io7m.jequality.AlmostEqualDouble;
import com.io7m.jnull.NullCheck;
import com.io7m.jtensors.VectorM3D;

/**
 * The handedness of a coordinate system.
 */

public enum CAxisSystemHandedness
{
  /**
   * The system is left-handed.
   */

  LEFT_HANDED,

  /**
   * The system is right-handed.
   */

  RIGHT_HANDED;

  /**
   * Determine the handedness of the coordinate system formed by the given axes.
   *
   * @param system The axes
   *
   * @return The handedness
   */

  public static CAxisSystemHandedness ofSystem(
    final CAxisSystem system)
  {
    NullCheck.notNull(system, "system");

    /*
     * Calculate the remaining "right" vector.
     */

    final VectorM3D right = new VectorM3D();
    VectorM3D.crossProduct(
      system.forward().vector(),
      system.up().vector(),
      right);

    final AlmostEqualDouble.ContextRelative c =
      new AlmostEqualDouble.ContextRelative();
    c.setMaxAbsoluteDifference(0.00000001);
    c.setMaxRelativeDifference(0.00000001);

    if (VectorM3D.almostEqual(c, system.right().vector(), right)) {
      return RIGHT_HANDED;
    }

    return LEFT_HANDED;
  }
}
