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

package com.io7m.jcoords.tests.core.conversion;

import com.io7m.jcoords.core.conversion.CAxis;
import com.io7m.jcoords.core.conversion.CAxisSystem;
import com.io7m.jcoords.core.conversion.CAxisSystemConversions;
import com.io7m.jtensors.core.unparameterized.matrices.Matrices3x3D;
import com.io7m.jtensors.core.unparameterized.matrices.Matrices4x4D;
import com.io7m.jtensors.core.unparameterized.matrices.Matrix3x3D;
import com.io7m.jtensors.core.unparameterized.matrices.Matrix4x4D;
import com.io7m.jtensors.core.unparameterized.vectors.Vector3D;
import com.io7m.jtensors.core.unparameterized.vectors.Vector4D;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.io7m.jequality.AlmostEqualDouble.ContextRelative;
import static com.io7m.jequality.AlmostEqualDouble.almostEqual;

public final class CAxisSystemConversionsTest
{
  private static final Logger LOG;

  static {
    LOG = LoggerFactory.getLogger(CAxisSystemConversionsTest.class);
  }

  @Test
  public void testAxesIdentity3x3()
  {
    final ContextRelative cr =
      new ContextRelative();
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

                final Matrix3x3D m_a =
                  CAxisSystemConversions.createMatrix3x3D(source, target);
                final Matrix3x3D m_b =
                  CAxisSystemConversions.createMatrix3x3D(target, source);

                final Vector3D v_src = Vector3D.of(
                  Math.random(), Math.random(), Math.random());

                final Vector3D v_dst_pre =
                  Matrices3x3D.multiplyVectorPost(m_a, v_src);

                LOG.debug("v_src:        {}", v_src);
                LOG.debug("v_dst (pre):  {}", v_dst_pre);

                final Vector3D v_dst_post =
                  Matrices3x3D.multiplyVectorPost(m_b, v_dst_pre);

                LOG.debug("v_dst (post): {}", v_dst_post);

                Assert.assertTrue(almostEqual(cr, v_src.x(), v_dst_post.x()));
                Assert.assertTrue(almostEqual(cr, v_src.y(), v_dst_post.y()));
                Assert.assertTrue(almostEqual(cr, v_src.z(), v_dst_post.z()));
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
    final ContextRelative cr =
      new ContextRelative();
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

                final Matrix4x4D m_a =
                  CAxisSystemConversions.createMatrix4x4D(source, target);
                final Matrix4x4D m_b =
                  CAxisSystemConversions.createMatrix4x4D(target, source);

                final Vector4D v_src = Vector4D.of(
                  Math.random(), Math.random(), Math.random(), Math.random());

                final Vector4D v_dst_pre =
                  Matrices4x4D.multiplyVectorPost(m_a, v_src);

                LOG.debug("v_src:        {}", v_src);
                LOG.debug("v_dst (pre):  {}", v_dst_pre);

                final Vector4D v_dst_post =
                  Matrices4x4D.multiplyVectorPost(m_b, v_dst_pre);

                LOG.debug("v_dst (post): {}", v_dst_post);

                Assert.assertTrue(almostEqual(cr, v_src.x(), v_dst_post.x()));
                Assert.assertTrue(almostEqual(cr, v_src.y(), v_dst_post.y()));
                Assert.assertTrue(almostEqual(cr, v_src.z(), v_dst_post.z()));
                Assert.assertTrue(almostEqual(cr, v_src.w(), v_dst_post.w()));
              }
            }
          }
        }
      }
    }
  }
}
