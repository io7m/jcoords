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
import com.io7m.jcoords.core.conversion.CAxisSystemHandedness;
import org.junit.Assert;
import org.junit.Test;

public final class CAxisSystemHandednessTest
{
  @Test
  public void testOpenGL()
  {
    final CAxisSystem.Builder system_b = CAxisSystem.builder();
    system_b.setRight(CAxis.AXIS_POSITIVE_X);
    system_b.setUp(CAxis.AXIS_POSITIVE_Y);
    system_b.setForward(CAxis.AXIS_NEGATIVE_Z);

    Assert.assertEquals(
      CAxisSystemHandedness.RIGHT_HANDED,
      CAxisSystemHandedness.ofSystem(system_b.build()));
  }

  @Test
  public void testBlender()
  {
    final CAxisSystem.Builder system_b = CAxisSystem.builder();
    system_b.setRight(CAxis.AXIS_POSITIVE_X);
    system_b.setUp(CAxis.AXIS_POSITIVE_Z);
    system_b.setForward(CAxis.AXIS_POSITIVE_Y);

    Assert.assertEquals(
      CAxisSystemHandedness.RIGHT_HANDED,
      CAxisSystemHandedness.ofSystem(system_b.build()));
  }

  @Test
  public void testDirectX()
  {
    final CAxisSystem.Builder system_b = CAxisSystem.builder();
    system_b.setRight(CAxis.AXIS_POSITIVE_X);
    system_b.setUp(CAxis.AXIS_POSITIVE_Y);
    system_b.setForward(CAxis.AXIS_POSITIVE_Z);

    Assert.assertEquals(
      CAxisSystemHandedness.LEFT_HANDED,
      CAxisSystemHandedness.ofSystem(system_b.build()));
  }
}
