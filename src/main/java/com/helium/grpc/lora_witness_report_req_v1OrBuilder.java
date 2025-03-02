// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_lora.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc;

public interface lora_witness_report_req_v1OrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_lora.lora_witness_report_req_v1)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bytes pub_key = 2;</code>
   * @return The pubKey.
   */
  com.google.protobuf.ByteString getPubKey();

  /**
   * <code>bytes data = 3;</code>
   * @return The data.
   */
  com.google.protobuf.ByteString getData();

  /**
   * <pre>
   * Timestamp of witness received in nanos since unix epoch
   * </pre>
   *
   * <code>uint64 timestamp = 4;</code>
   * @return The timestamp.
   */
  long getTimestamp();

  /**
   * <code>uint32 tmst = 5;</code>
   * @return The tmst.
   */
  int getTmst();

  /**
   * <pre>
   * Signal in ddBm
   * </pre>
   *
   * <code>sint32 signal = 6;</code>
   * @return The signal.
   */
  int getSignal();

  /**
   * <pre>
   * SNR in ddB
   * </pre>
   *
   * <code>int32 snr = 7;</code>
   * @return The snr.
   */
  int getSnr();

  /**
   * <pre>
   * Frequency in Hz
   * </pre>
   *
   * <code>uint64 frequency = 8;</code>
   * @return The frequency.
   */
  long getFrequency();

  /**
   * <code>.helium.data_rate datarate = 10;</code>
   * @return The enum numeric value on the wire for datarate.
   */
  int getDatarateValue();
  /**
   * <code>.helium.data_rate datarate = 10;</code>
   * @return The datarate.
   */
  com.helium.grpc.data_rate getDatarate();

  /**
   * <code>bytes signature = 11;</code>
   * @return The signature.
   */
  com.google.protobuf.ByteString getSignature();
}
