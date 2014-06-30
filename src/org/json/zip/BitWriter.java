/* 
 * The MIT License
 *
 * Copyright 2014 Halcyon <support@planetcrypto.com A2A1A088>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.json.zip;

import java.io.IOException;

/**
 * A bitwriter is a an interface that allows for doing output at the bit level.
 * Most IO interfaces only allow for writing at the byte level or higher.
 */
public interface BitWriter {

    /**
     * Write a 1 bit.
     *
     * @throws IOException
     */
    public void one() throws IOException;

    /**
     * Pad the rest of the block with zeros and flush.
     *
     * @param width
     *            The size in bits of the block to pad. This will typically be
     *            8, 16, 32, 64, 128, 256, etc.
     * @throws IOException
     */
    public void pad(int width) throws IOException;

    /**
     * Write some bits. Up to 32 bits can be written at a time.
     *
     * @param bits
     *            The bits to be written.
     * @param width
     *            The number of bits to write. (0..32)
     * @throws IOException
     */
    public void write(int bits, int width) throws IOException;

    /**
     * Write a 0 bit.
     *
     * @throws IOException
     */
    public void zero() throws IOException;
}
