// Targeted by JavaCPP version 1.5.1-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.ffmpeg.avformat;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import org.bytedeco.ffmpeg.avutil.*;
import static org.bytedeco.ffmpeg.global.avutil.*;
import org.bytedeco.ffmpeg.swresample.*;
import static org.bytedeco.ffmpeg.global.swresample.*;
import org.bytedeco.ffmpeg.avcodec.*;
import static org.bytedeco.ffmpeg.global.avcodec.*;

import static org.bytedeco.ffmpeg.global.avformat.*;


@Properties(inherit = org.bytedeco.ffmpeg.presets.avformat.class)
public class AVOpenCallback extends FunctionPointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public    AVOpenCallback(Pointer p) { super(p); }
    protected AVOpenCallback() { allocate(); }
    private native void allocate();
    public native int call(AVFormatContext s, @ByPtrPtr AVIOContext pb, @Cast("const char*") BytePointer url, int flags,
                              @Const AVIOInterruptCB int_cb, @ByPtrPtr AVDictionary options);
}
