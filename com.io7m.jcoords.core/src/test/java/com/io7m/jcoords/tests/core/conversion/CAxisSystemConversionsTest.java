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

package com.io7m.jcoords.tests.core.conversion;

import com.io7m.jcoords.core.conversion.CAxis;
import com.io7m.jcoords.core.conversion.CAxisSystem;
import com.io7m.jcoords.core.conversion.CAxisSystemConversions;
import com.io7m.jequality.AlmostEqualDouble;
import com.io7m.jtensors.Matrix3x3DType;
import com.io7m.jtensors.Matrix4x4DType;
import com.io7m.jtensors.MatrixM3x3D;
import com.io7m.jtensors.MatrixM4x4D;
import com.io7m.jtensors.VectorI3D;
import com.io7m.jtensors.VectorI4D;
import com.io7m.jtensors.VectorM3D;
import com.io7m.jtensors.VectorM4D;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CAxisSystemConversionsTest
{
  private static final Logger LOG;

  static {
    LOG = LoggerFactory.getLogger(CAxisSystemConversionsTest.class);
  }

  @Test
  public void testAxesIdentity3x3()
  {
    final MatrixM3x3D.ContextMM3D c =
      new MatrixM3x3D.ContextMM3D();

    final AlmostEqualDouble.ContextRelative cr =
      new AlmostEqualDouble.ContextRelative();
    cr.setMaxRelativeDifference(0.0000001);
    cr.setMaxAbsoluteDifference(0.0000001);

    for (final CAxis source_right : CAxis.values()) {
      for (final CAxis source_up : CAxis.values()) {
        for (final CAxis source_forward : CAxis.values()) {
          if (source_right.axis() == source_up.axis()) {
            continue;
          }
          if (source_right.axis() == source_forward.axis()) {
            continue;
          }
          if (source_up.axis() == source_forward.axis()) {
            continue;
          }

          for (final CAxis target_right : CAxis.values()) {
            for (final CAxis target_up : CAxis.values()) {
              for (final CAxis target_forward : CAxis.values()) {
                if (target_right.axis() == target_up.axis()) {
                  continue;
                }
                if (target_right.axis() == target_forward.axis()) {
                  continue;
                }
                if (target_up.axis() == target_forward.axis()) {
                  continue;
                }

                final CAxisSystem.Builder source_b = CAxisSystem.builder();
                source_b.setRight(source_right);
                source_b.setUp(source_up);
                source_b.setForward(source_forward);
                final CAxisSystem source = source_b.build();

                final CAxisSystem.Builder target_b = CAxisSystem.builder();
                target_b.setRight(target_right);
                target_b.setUp(target_up);
                target_b.setForward(target_forward);
                final CAxisSystem target = target_b.build();

                final Matrix3x3DType m_a =
                  CAxisSystemConversions.createMatrix3x3D(c, source, target);
                final Matrix3x3DType m_b =
                  CAxisSystemConversions.createMatrix3x3D(c, target, source);

                final VectorI3D v_src =
                  new VectorI3D(Math.random(), Math.random(), Math.random());

                final VectorM3D v_dst = new VectorM3D();

                MatrixM3x3D.multiplyVector3D(c, m_a, v_src, v_dst);
                LOG.debug("v_src:        {}", v_src);
                LOG.debug("v_dst (pre):  {}", v_dst);
                MatrixM3x3D.multiplyVector3D(c, m_b, v_dst, v_dst);
                LOG.debug("v_dst (post): {}", v_dst);

                Assert.assertTrue(VectorM3D.almostEqual(cr, v_src, v_dst));
              }
            }
          }
        }
      }
    }
  }

  @Test
  public void testAxesIdentity4x4()
  {
    final MatrixM4x4D.ContextMM4D c =
      new MatrixM4x4D.ContextMM4D();

    final AlmostEqualDouble.ContextRelative cr =
      new AlmostEqualDouble.ContextRelative();
    cr.setMaxRelativeDifference(0.0000001);
    cr.setMaxAbsoluteDifference(0.0000001);

    for (final CAxis source_right : CAxis.values()) {
      for (final CAxis source_up : CAxis.values()) {
        for (final CAxis source_forward : CAxis.values()) {
          if (source_right.axis() == source_up.axis()) {
            continue;
          }
          if (source_right.axis() == source_forward.axis()) {
            continue;
          }
          if (source_up.axis() == source_forward.axis()) {
            continue;
          }

          for (final CAxis target_right : CAxis.values()) {
            for (final CAxis target_up : CAxis.values()) {
              for (final CAxis target_forward : CAxis.values()) {
                if (target_right.axis() == target_up.axis()) {
                  continue;
                }
                if (target_right.axis() == target_forward.axis()) {
                  continue;
                }
                if (target_up.axis() == target_forward.axis()) {
                  continue;
                }

                final CAxisSystem.Builder source_b = CAxisSystem.builder();
                source_b.setRight(source_right);
                source_b.setUp(source_up);
                source_b.setForward(source_forward);
                final CAxisSystem source = source_b.build();

                final CAxisSystem.Builder target_b = CAxisSystem.builder();
                target_b.setRight(target_right);
                target_b.setUp(target_up);
                target_b.setForward(target_forward);
                final CAxisSystem target = target_b.build();

                final Matrix4x4DType m_a =
                  CAxisSystemConversions.createMatrix4x4D(c, source, target);
                final Matrix4x4DType m_b =
                  CAxisSystemConversions.createMatrix4x4D(c, target, source);

                final VectorI4D v_src = new VectorI4D(
                  Math.random(), Math.random(), Math.random(), Math.random());

                final VectorM4D v_dst = new VectorM4D();

                MatrixM4x4D.multiplyVector4D(c, m_a, v_src, v_dst);
                LOG.debug("v_src:        {}", v_src);
                LOG.debug("v_dst (pre):  {}", v_dst);
                MatrixM4x4D.multiplyVector4D(c, m_b, v_dst, v_dst);
                LOG.debug("v_dst (post): {}", v_dst);

                Assert.assertTrue(VectorM4D.almostEqual(cr, v_src, v_dst));
              }
            }
          }
        }
      }
    }
  }
}
