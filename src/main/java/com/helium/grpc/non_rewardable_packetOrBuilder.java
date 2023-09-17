// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/poc_lora.proto

package com.helium.grpc;

public interface non_rewardable_packetOrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_lora.non_rewardable_packet)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.helium.packet_verifier.valid_packet packet = 1;</code>
   * @return Whether the packet field is set.
   */
  boolean hasPacket();
  /**
   * <code>.helium.packet_verifier.valid_packet packet = 1;</code>
   * @return The packet.
   */
  valid_packet getPacket();
  /**
   * <code>.helium.packet_verifier.valid_packet packet = 1;</code>
   */
  valid_packetOrBuilder getPacketOrBuilder();

  /**
   * <code>.helium.poc_lora.non_rewardable_packet_reason reason = 2;</code>
   * @return The enum numeric value on the wire for reason.
   */
  int getReasonValue();
  /**
   * <code>.helium.poc_lora.non_rewardable_packet_reason reason = 2;</code>
   * @return The reason.
   */
  non_rewardable_packet_reason getReason();

  /**
   * <pre>
   * timestamp in millis of when the packet was determined non rewardable
   * </pre>
   *
   * <code>uint64 timestamp = 3;</code>
   * @return The timestamp.
   */
  long getTimestamp();
}
