// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_mobile.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

public interface speedtest_req_v1OrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_mobile.speedtest_req_v1)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bytes pub_key = 1;</code>
   * @return The pubKey.
   */
  com.google.protobuf.ByteString getPubKey();

  /**
   * <code>string serial = 2;</code>
   * @return The serial.
   */
  java.lang.String getSerial();
  /**
   * <code>string serial = 2;</code>
   * @return The bytes for serial.
   */
  com.google.protobuf.ByteString
      getSerialBytes();

  /**
   * <pre>
   * &#47; Timestamp of speedtest test in seconds from unix epoch
   * </pre>
   *
   * <code>uint64 timestamp = 3;</code>
   * @return The timestamp.
   */
  long getTimestamp();

  /**
   * <pre>
   * &#47; Measured upload speed in bytes/sec. &lt;value&gt;*8/10E5 = Mbps
   * </pre>
   *
   * <code>uint64 upload_speed = 4;</code>
   * @return The uploadSpeed.
   */
  long getUploadSpeed();

  /**
   * <pre>
   * &#47; Measured download speed in bytes/sec. &lt;value&gt;*8/10E5 = Mbps
   * </pre>
   *
   * <code>uint64 download_speed = 5;</code>
   * @return The downloadSpeed.
   */
  long getDownloadSpeed();

  /**
   * <pre>
   * &#47; Measured latency in milliseconds
   * </pre>
   *
   * <code>uint32 latency = 6;</code>
   * @return The latency.
   */
  int getLatency();

  /**
   * <code>bytes signature = 7;</code>
   * @return The signature.
   */
  com.google.protobuf.ByteString getSignature();
}
