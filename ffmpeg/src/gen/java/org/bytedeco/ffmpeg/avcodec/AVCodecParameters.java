// Targeted by JavaCPP version 1.5.1-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.ffmpeg.avcodec;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import org.bytedeco.ffmpeg.avutil.*;
import static org.bytedeco.ffmpeg.global.avutil.*;

import static org.bytedeco.ffmpeg.global.avcodec.*;


/**
 * This struct describes the properties of an encoded stream.
 *
 * sizeof(AVCodecParameters) is not a part of the public ABI, this struct must
 * be allocated with avcodec_parameters_alloc() and freed with
 * avcodec_parameters_free().
 */
@Properties(inherit = org.bytedeco.ffmpeg.presets.avcodec.class)
public class AVCodecParameters extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public AVCodecParameters() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public AVCodecParameters(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public AVCodecParameters(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public AVCodecParameters position(long position) {
        return (AVCodecParameters)super.position(position);
    }

    /**
     * General type of the encoded data.
     */
    public native @Cast("AVMediaType") int codec_type(); public native AVCodecParameters codec_type(int setter);
    /**
     * Specific type of the encoded data (the codec used).
     */
    public native @Cast("AVCodecID") int codec_id(); public native AVCodecParameters codec_id(int setter);
    /**
     * Additional information about the codec (corresponds to the AVI FOURCC).
     */
    public native @Cast("uint32_t") int codec_tag(); public native AVCodecParameters codec_tag(int setter);

    /**
     * Extra binary data needed for initializing the decoder, codec-dependent.
     *
     * Must be allocated with av_malloc() and will be freed by
     * avcodec_parameters_free(). The allocated size of extradata must be at
     * least extradata_size + AV_INPUT_BUFFER_PADDING_SIZE, with the padding
     * bytes zeroed.
     */
    public native @Cast("uint8_t*") BytePointer extradata(); public native AVCodecParameters extradata(BytePointer setter);
    /**
     * Size of the extradata content in bytes.
     */
    public native int extradata_size(); public native AVCodecParameters extradata_size(int setter);

    /**
     * - video: the pixel format, the value corresponds to enum AVPixelFormat.
     * - audio: the sample format, the value corresponds to enum AVSampleFormat.
     */
    public native int format(); public native AVCodecParameters format(int setter);

    /**
     * The average bitrate of the encoded data (in bits per second).
     */
    public native @Cast("int64_t") long bit_rate(); public native AVCodecParameters bit_rate(long setter);

    /**
     * The number of bits per sample in the codedwords.
     *
     * This is basically the bitrate per sample. It is mandatory for a bunch of
     * formats to actually decode them. It's the number of bits for one sample in
     * the actual coded bitstream.
     *
     * This could be for example 4 for ADPCM
     * For PCM formats this matches bits_per_raw_sample
     * Can be 0
     */
    public native int bits_per_coded_sample(); public native AVCodecParameters bits_per_coded_sample(int setter);

    /**
     * This is the number of valid bits in each output sample. If the
     * sample format has more bits, the least significant bits are additional
     * padding bits, which are always 0. Use right shifts to reduce the sample
     * to its actual size. For example, audio formats with 24 bit samples will
     * have bits_per_raw_sample set to 24, and format set to AV_SAMPLE_FMT_S32.
     * To get the original sample use "(int32_t)sample >> 8"."
     *
     * For ADPCM this might be 12 or 16 or similar
     * Can be 0
     */
    public native int bits_per_raw_sample(); public native AVCodecParameters bits_per_raw_sample(int setter);

    /**
     * Codec-specific bitstream restrictions that the stream conforms to.
     */
    public native int profile(); public native AVCodecParameters profile(int setter);
    public native int level(); public native AVCodecParameters level(int setter);

    /**
     * Video only. The dimensions of the video frame in pixels.
     */
    public native int width(); public native AVCodecParameters width(int setter);
    public native int height(); public native AVCodecParameters height(int setter);

    /**
     * Video only. The aspect ratio (width / height) which a single pixel
     * should have when displayed.
     *
     * When the aspect ratio is unknown / undefined, the numerator should be
     * set to 0 (the denominator may have any value).
     */
    public native @ByRef AVRational sample_aspect_ratio(); public native AVCodecParameters sample_aspect_ratio(AVRational setter);

    /**
     * Video only. The order of the fields in interlaced video.
     */
    public native @Cast("AVFieldOrder") int field_order(); public native AVCodecParameters field_order(int setter);

    /**
     * Video only. Additional colorspace characteristics.
     */
    public native @Cast("AVColorRange") int color_range(); public native AVCodecParameters color_range(int setter);
    public native @Cast("AVColorPrimaries") int color_primaries(); public native AVCodecParameters color_primaries(int setter);
    public native @Cast("AVColorTransferCharacteristic") int color_trc(); public native AVCodecParameters color_trc(int setter);
    public native @Cast("AVColorSpace") int color_space(); public native AVCodecParameters color_space(int setter);
    public native @Cast("AVChromaLocation") int chroma_location(); public native AVCodecParameters chroma_location(int setter);

    /**
     * Video only. Number of delayed frames.
     */
    public native int video_delay(); public native AVCodecParameters video_delay(int setter);

    /**
     * Audio only. The channel layout bitmask. May be 0 if the channel layout is
     * unknown or unspecified, otherwise the number of bits set must be equal to
     * the channels field.
     */
    public native @Cast("uint64_t") long channel_layout(); public native AVCodecParameters channel_layout(long setter);
    /**
     * Audio only. The number of audio channels.
     */
    public native int channels(); public native AVCodecParameters channels(int setter);
    /**
     * Audio only. The number of audio samples per second.
     */
    public native int sample_rate(); public native AVCodecParameters sample_rate(int setter);
    /**
     * Audio only. The number of bytes per coded audio frame, required by some
     * formats.
     *
     * Corresponds to nBlockAlign in WAVEFORMATEX.
     */
    public native int block_align(); public native AVCodecParameters block_align(int setter);
    /**
     * Audio only. Audio frame size, if known. Required by some formats to be static.
     */
    public native int frame_size(); public native AVCodecParameters frame_size(int setter);

    /**
     * Audio only. The amount of padding (in samples) inserted by the encoder at
     * the beginning of the audio. I.e. this number of leading decoded samples
     * must be discarded by the caller to get the original audio without leading
     * padding.
     */
    public native int initial_padding(); public native AVCodecParameters initial_padding(int setter);
    /**
     * Audio only. The amount of padding (in samples) appended by the encoder to
     * the end of the audio. I.e. this number of decoded samples must be
     * discarded by the caller from the end of the stream to get the original
     * audio without any trailing padding.
     */
    public native int trailing_padding(); public native AVCodecParameters trailing_padding(int setter);
    /**
     * Audio only. Number of samples to skip after a discontinuity.
     */
    public native int seek_preroll(); public native AVCodecParameters seek_preroll(int setter);
}
