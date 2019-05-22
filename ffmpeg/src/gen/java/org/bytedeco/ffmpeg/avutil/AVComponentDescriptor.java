// Targeted by JavaCPP version 1.5.1-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.ffmpeg.avutil;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.ffmpeg.global.avutil.*;


@Properties(inherit = org.bytedeco.ffmpeg.presets.avutil.class)
public class AVComponentDescriptor extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public AVComponentDescriptor() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public AVComponentDescriptor(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public AVComponentDescriptor(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public AVComponentDescriptor position(long position) {
        return (AVComponentDescriptor)super.position(position);
    }

    /**
     * Which of the 4 planes contains the component.
     */
    public native int plane(); public native AVComponentDescriptor plane(int setter);

    /**
     * Number of elements between 2 horizontally consecutive pixels.
     * Elements are bits for bitstream formats, bytes otherwise.
     */
    public native int step(); public native AVComponentDescriptor step(int setter);

    /**
     * Number of elements before the component of the first pixel.
     * Elements are bits for bitstream formats, bytes otherwise.
     */
    public native int offset(); public native AVComponentDescriptor offset(int setter);

    /**
     * Number of least significant bits that must be shifted away
     * to get the value.
     */
    public native int shift(); public native AVComponentDescriptor shift(int setter);

    /**
     * Number of bits in the component.
     */
    public native int depth(); public native AVComponentDescriptor depth(int setter);

// #if FF_API_PLUS1_MINUS1
    /** deprecated, use step instead */
    public native @Deprecated int step_minus1(); public native AVComponentDescriptor step_minus1(int setter);

    /** deprecated, use depth instead */
    public native @Deprecated int depth_minus1(); public native AVComponentDescriptor depth_minus1(int setter);

    /** deprecated, use offset instead */
    public native @Deprecated int offset_plus1(); public native AVComponentDescriptor offset_plus1(int setter);
// #endif
}
