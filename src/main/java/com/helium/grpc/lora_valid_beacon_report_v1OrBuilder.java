// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/poc_lora.proto

package com.helium.grpc;

public interface lora_valid_beacon_report_v1OrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_lora.lora_valid_beacon_report_v1)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Timestamp at ingest in millis since unix epoch
   * </pre>
   *
   * <code>uint64 received_timestamp = 1;</code>
   * @return The receivedTimestamp.
   */
  long getReceivedTimestamp();

  /**
   * <pre>
   * string representation of the gateways u64 hex location
   * </pre>
   *
   * <code>string location = 2;</code>
   * @return The location.
   */
  java.lang.String getLocation();
  /**
   * <pre>
   * string representation of the gateways u64 hex location
   * </pre>
   *
   * <code>string location = 2;</code>
   * @return The bytes for location.
   */
  com.google.protobuf.ByteString
      getLocationBytes();

  /**
   * <pre>
   * integer representation of a 4-point precision decimal multiplier
   * ex: 5015 == 0.5015
   * </pre>
   *
   * <code>uint32 hex_scale = 3;</code>
   * @return The hexScale.
   */
  int getHexScale();

  /**
   * <code>.helium.poc_lora.lora_beacon_report_req_v1 report = 4;</code>
   * @return Whether the report field is set.
   */
  boolean hasReport();
  /**
   * <code>.helium.poc_lora.lora_beacon_report_req_v1 report = 4;</code>
   * @return The report.
   */
  com.helium.grpc.lora_beacon_report_req_v1 getReport();
  /**
   * <code>.helium.poc_lora.lora_beacon_report_req_v1 report = 4;</code>
   */
  com.helium.grpc.lora_beacon_report_req_v1OrBuilder getReportOrBuilder();

  /**
   * <pre>
   * integer representation of a 4-point precision decimal multiplier
   * based on the number of witnesses to a poc event
   * </pre>
   *
   * <code>uint32 reward_unit = 5;</code>
   * @return The rewardUnit.
   */
  int getRewardUnit();

  /**
   * <pre>
   *&#47; the transmit gain value of the gateway in dbi x 10
   * / For example 1 dbi = 10, 15 dbi = 150
   * / derived from gateway metadata
   * </pre>
   *
   * <code>int32 gain = 6;</code>
   * @return The gain.
   */
  int getGain();

  /**
   * <pre>
   *&#47; The asserted elevation of the gateway in AGL ( above ground level)
   * / derived from gateway metadata
   * </pre>
   *
   * <code>int32 elevation = 7;</code>
   * @return The elevation.
   */
  int getElevation();
}
