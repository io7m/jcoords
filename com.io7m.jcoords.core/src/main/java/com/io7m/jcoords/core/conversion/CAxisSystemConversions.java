/*
 * Copyright © 2016 Mark Raynsford <code@io7m.com> https://www.io7m.com
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

import com.io7m.jaffirm.core.Invariants;
import com.io7m.jtensors.core.unparameterized.matrices.Matrices3x3D;
import com.io7m.jtensors.core.unparameterized.matrices.Matrices4x4D;
import com.io7m.jtensors.core.unparameterized.matrices.Matrix3x3D;
import com.io7m.jtensors.core.unparameterized.matrices.Matrix4x4D;
import com.io7m.junreachable.UnreachableCodeException;

import java.util.Optional;

/**
 * Functions to produce matrices to convert between coordinate spaces.
 */

public final class CAxisSystemConversions
{
  private CAxisSystemConversions()
  {
    throw new UnreachableCodeException();
  }

  /**
   * Construct a 3x3 matrix to convert coordinates in system {@code source}
   * to system {@code target}.
   *
   * @param source The source coordinate system
   * @param target The target coordinate system
   *
   * @return A new matrix
   */

  public static Matrix3x3D createMatrix3x3D(
    final CAxisSystemType source,
    final CAxisSystemType target)
  {
    final Optional<Matrix3x3D> m_source_inv_opt =
      Matrices3x3D.invert(source.basis3x3());

    Invariants.checkInvariant(
      m_source_inv_opt.isPresent(),
      "Coordinate system matrix must be invertible");

    return Matrices3x3D.multiply(target.basis3x3(), m_source_inv_opt.get());
  }

  /**
   * Construct a 4x4 matrix to convert coordinates in system {@code source}
   * to system {@code target}.
   *
   * @param source The source coordinate system
   * @param target The target coordinate system
   *
   * @return A new matrix
   */

  public static Matrix4x4D createMatrix4x4D(
    final CAxisSystemType source,
    final CAxisSystemType target)
  {
    final Optional<Matrix4x4D> m_source_inv_opt =
      Matrices4x4D.invert(source.basis4x4());

    Invariants.checkInvariant(
      m_source_inv_opt.isPresent(),
      "Coordinate system matrix must be invertible");

    return Matrices4x4D.multiply(target.basis4x4(), m_source_inv_opt.get());
  }
}
