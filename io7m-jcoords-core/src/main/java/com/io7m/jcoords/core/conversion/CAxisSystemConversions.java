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

import com.io7m.jtensors.Matrix3x3DType;
import com.io7m.jtensors.Matrix4x4DType;
import com.io7m.jtensors.MatrixHeapArrayM3x3D;
import com.io7m.jtensors.MatrixHeapArrayM4x4D;
import com.io7m.jtensors.MatrixM3x3D;
import com.io7m.jtensors.MatrixM4x4D;
import com.io7m.junreachable.UnreachableCodeException;

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
   * @param context A matrix context
   * @param source  The source coordinate system
   * @param target  The target coordinate system
   *
   * @return A new matrix
   */

  public static Matrix3x3DType createMatrix3x3D(
    final MatrixM3x3D.ContextMM3D context,
    final CAxisSystemType source,
    final CAxisSystemType target)
  {
    final Matrix3x3DType m_source_inv = source.basis();
    MatrixM3x3D.invertInPlace(context, m_source_inv);
    final Matrix3x3DType m_target = target.basis();
    final Matrix3x3DType full = MatrixHeapArrayM3x3D.newMatrix();
    MatrixM3x3D.multiply(m_target, m_source_inv, full);
    return full;
  }

  /**
   * Construct a 4x4 matrix to convert coordinates in system {@code source}
   * to system {@code target}.
   *
   * @param context A matrix context
   * @param source  The source coordinate system
   * @param target  The target coordinate system
   *
   * @return A new matrix
   */

  public static Matrix4x4DType createMatrix4x4D(
    final MatrixM4x4D.ContextMM4D context,
    final CAxisSystemType source,
    final CAxisSystemType target)
  {
    final Matrix3x3DType m_source = source.basis();
    final Matrix4x4DType m_source_inv = MatrixHeapArrayM4x4D.newMatrix();
    for (int column = 0; column < 3; ++column) {
      for (int row = 0; row < 3; ++row) {
        m_source_inv.setRowColumnD(
          row, column, m_source.getRowColumnD(row, column));
      }
    }

    MatrixM4x4D.invertInPlace(context, m_source_inv);

    final Matrix3x3DType m_target = target.basis();
    final Matrix4x4DType m_target_4x4 = MatrixHeapArrayM4x4D.newMatrix();
    for (int column = 0; column < 3; ++column) {
      for (int row = 0; row < 3; ++row) {
        m_target_4x4.setRowColumnD(
          row, column, m_target.getRowColumnD(row, column));
      }
    }

    final Matrix4x4DType full = MatrixHeapArrayM4x4D.newMatrix();
    MatrixM4x4D.multiply(m_target_4x4, m_source_inv, full);
    return full;
  }
}
