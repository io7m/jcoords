/*
 * Copyright © 2017 Mark Raynsford <code@io7m.com> https://www.io7m.com
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
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public final class CAxisTest
{
  @Rule public ExpectedException expected = ExpectedException.none();

  @Test
  public void testAxisSign()
  {
    Assert.assertEquals("+x", CAxis.AXIS_POSITIVE_X.axisSigned());
    Assert.assertEquals("+y", CAxis.AXIS_POSITIVE_Y.axisSigned());
    Assert.assertEquals("+z", CAxis.AXIS_POSITIVE_Z.axisSigned());
    Assert.assertEquals("-x", CAxis.AXIS_NEGATIVE_X.axisSigned());
    Assert.assertEquals("-y", CAxis.AXIS_NEGATIVE_Y.axisSigned());
    Assert.assertEquals("-z", CAxis.AXIS_NEGATIVE_Z.axisSigned());
  }

  @Test
  public void testAxisParse()
  {
    Assert.assertEquals(CAxis.of("+x"), CAxis.AXIS_POSITIVE_X);
    Assert.assertEquals(CAxis.of("+y"), CAxis.AXIS_POSITIVE_Y);
    Assert.assertEquals(CAxis.of("+z"), CAxis.AXIS_POSITIVE_Z);
    Assert.assertEquals(CAxis.of("-x"), CAxis.AXIS_NEGATIVE_X);
    Assert.assertEquals(CAxis.of("-y"), CAxis.AXIS_NEGATIVE_Y);
    Assert.assertEquals(CAxis.of("-z"), CAxis.AXIS_NEGATIVE_Z);
  }

  @Test
  public void testAxisParseBad()
  {
    this.expected.expect(IllegalArgumentException.class);
    CAxis.of("q");
  }
}
