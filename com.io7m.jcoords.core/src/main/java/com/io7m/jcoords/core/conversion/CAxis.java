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
import com.io7m.jtensors.core.unparameterized.vectors.Vector3D;

/**
 * An axis.
 */

public enum CAxis
{
  /**
   * The positive X axis.
   */

  AXIS_POSITIVE_X('x', true, Vector3D.of(1.0, 0.0, 0.0)),

  /**
   * The positive Y axis.
   */

  AXIS_POSITIVE_Y('y', true, Vector3D.of(0.0, 1.0, 0.0)),

  /**
   * The positive Z axis.
   */

  AXIS_POSITIVE_Z('z', true, Vector3D.of(0.0, 0.0, 1.0)),

  /**
   * The negative X axis.
   */

  AXIS_NEGATIVE_X('x', false, Vector3D.of(-1.0, 0.0, 0.0)),

  /**
   * The negative Y axis.
   */

  AXIS_NEGATIVE_Y('y', false, Vector3D.of(0.0, -1.0, 0.0)),

  /**
   * The negative Z axis.
   */

  AXIS_NEGATIVE_Z('z', false, Vector3D.of(0.0, 0.0, -1.0)),;

  private final int name;
  private final Vector3D vector;
  private final boolean positive;

  CAxis(
    final int in_name,
    final boolean in_positive,
    final Vector3D in_v)
  {
    this.name = in_name;
    this.positive = in_positive;
    this.vector = NullCheck.notNull(in_v, "Vector");
  }

  /**
   * Parse an axis.
   *
   * @param name The axis as "+x", "-x", "+y", "-y", "+z", or "-z"
   *
   * @return A parsed axis
   *
   * @throws IllegalArgumentException If the axis cannot be parsed
   */

  public static CAxis of(
    final String name)
    throws IllegalArgumentException
  {
    switch (name) {
      case "+x":
        return AXIS_POSITIVE_X;
      case "+y":
        return AXIS_POSITIVE_Y;
      case "+z":
        return AXIS_POSITIVE_Z;
      case "-x":
        return AXIS_NEGATIVE_X;
      case "-y":
        return AXIS_NEGATIVE_Y;
      case "-z":
        return AXIS_NEGATIVE_Z;
      default: {
        throw new IllegalArgumentException("Unrecognized axis name: " + name);
      }
    }
  }

  /**
   * @return The axis as 'x', 'y' or 'z'
   */

  public int axis()
  {
    return this.name;
  }

  /**
   * @return The axis as "+x", "-x", "+y", "-y", "+z", or "-z"
   */

  public String axisSigned()
  {
    return this.positive ? "+" + (char) this.axis() : "-" + (char) this.axis();
  }

  /**
   * @return The vector for the axis
   */

  public Vector3D vector()
  {
    return this.vector;
  }
}
