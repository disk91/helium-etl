// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_lora.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc;

public interface lora_beacon_ingest_report_v1OrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_lora.lora_beacon_ingest_report_v1)
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
   * <code>.helium.poc_lora.lora_beacon_report_req_v1 report = 2;</code>
   * @return Whether the report field is set.
   */
  boolean hasReport();
  /**
   * <code>.helium.poc_lora.lora_beacon_report_req_v1 report = 2;</code>
   * @return The report.
   */
  com.helium.grpc.lora_beacon_report_req_v1 getReport();
  /**
   * <code>.helium.poc_lora.lora_beacon_report_req_v1 report = 2;</code>
   */
  com.helium.grpc.lora_beacon_report_req_v1OrBuilder getReportOrBuilder();
}
