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

public interface BitReader {

    /**
     * Read one bit.
     *
     * @return true if it is a 1 bit.
     */
    public boolean bit() throws IOException;

    /**
     * Returns the number of bits that have been read from this bitreader.
     *
     * @return The number of bits read so far.
     */
    public long nrBits();

    /**
     * Check that the rest of the block has been padded with zeros.
     *
     * @param width
     *            The size in bits of the block to pad. This will typically be
     *            8, 16, 32, 64, 128, 256, etc.
     * @return true if the block was zero padded, or false if the the padding
     *         contained any one bits.
     * @throws IOException
     */
    public boolean pad(int width) throws IOException;

    /**
     * Read some bits.
     *
     * @param width
     *            The number of bits to read. (0..32)
     * @throws IOException
     * @return the bits
     */
    public int read(int width) throws IOException;
}
