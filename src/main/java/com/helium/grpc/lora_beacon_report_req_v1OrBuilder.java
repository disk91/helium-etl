// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/poc_lora.proto

package com.helium.grpc;

public interface lora_beacon_report_req_v1OrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_lora.lora_beacon_report_req_v1)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bytes pub_key = 2;</code>
   * @return The pubKey.
   */
  com.google.protobuf.ByteString getPubKey();

  /**
   * <code>bytes local_entropy = 3;</code>
   * @return The localEntropy.
   */
  com.google.protobuf.ByteString getLocalEntropy();

  /**
   * <code>bytes remote_entropy = 4;</code>
   * @return The remoteEntropy.
   */
  com.google.protobuf.ByteString getRemoteEntropy();

  /**
   * <code>bytes data = 5;</code>
   * @return The data.
   */
  com.google.protobuf.ByteString getData();

  /**
   * <pre>
   * Frequency in Hz
   * </pre>
   *
   * <code>uint64 frequency = 6;</code>
   * @return The frequency.
   */
  long getFrequency();

  /**
   * <code>int32 channel = 7;</code>
   * @return The channel.
   */
  int getChannel();

  /**
   * <code>.helium.data_rate datarate = 8;</code>
   * @return The enum numeric value on the wire for datarate.
   */
  int getDatarateValue();
  /**
   * <code>.helium.data_rate datarate = 8;</code>
   * @return The datarate.
   */
  data_rate getDatarate();

  /**
   * <pre>
   * The Conducted transmit power in ddbm. This is _not_ adjusted with the
   * asserted gain of the gateway
   * </pre>
   *
   * <code>int32 tx_power = 9;</code>
   * @return The txPower.
   */
  int getTxPower();

  /**
   * <pre>
   * Timestamp of beacon transmit in nanos since unix epoch
   * </pre>
   *
   * <code>uint64 timestamp = 10;</code>
   * @return The timestamp.
   */
  long getTimestamp();

  /**
   * <code>bytes signature = 11;</code>
   * @return The signature.
   */
  com.google.protobuf.ByteString getSignature();

  /**
   * <code>uint32 tmst = 12;</code>
   * @return The tmst.
   */
  int getTmst();
}
