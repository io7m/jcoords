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

import com.io7m.jaffirm.core.PreconditionViolationException;
import com.io7m.jcoords.core.conversion.CAxis;
import com.io7m.jcoords.core.conversion.CAxisSystem;
import org.junit.Assert;
import org.junit.Test;

public final class CAxisSystemTest
{
  @Test
  public void testAxesNoConflict()
  {
    for (final CAxis right : CAxis.values()) {
      for (final CAxis up : CAxis.values()) {
        for (final CAxis forward : CAxis.values()) {
          if (right.axis() == up.axis()) {
            continue;
          }
          if (right.axis() == forward.axis()) {
            continue;
          }
          if (up.axis() == forward.axis()) {
            continue;
          }

          final CAxisSystem system = CAxisSystem.of(right, up, forward);
          Assert.assertEquals(right, system.right());
          Assert.assertEquals(up, system.up());
          Assert.assertEquals(forward, system.forward());
        }
      }
    }
  }

  @Test
  public void testAxesConflict()
  {
    int count = 0;

    for (final CAxis right : CAxis.values()) {
      for (final CAxis up : CAxis.values()) {
        for (final CAxis forward : CAxis.values()) {
          if (right.axis() == up.axis() && up.axis() == forward.axis()) {
            try {
              CAxisSystem.of(right, up, forward);
            } catch (final PreconditionViolationException e) {
              ++count;
            }
          }
        }
      }
    }

    Assert.assertEquals(24L, (long) count);
  }
}
