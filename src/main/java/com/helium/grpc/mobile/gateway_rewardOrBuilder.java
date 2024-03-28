// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/poc_mobile.proto

// Protobuf Java Version: 3.25.1
package com.helium.grpc.mobile;

public interface gateway_rewardOrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_mobile.gateway_reward)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *&#47; Public key of the hotspot
   * </pre>
   *
   * <code>bytes hotspot_key = 1;</code>
   * @return The hotspotKey.
   */
  com.google.protobuf.ByteString getHotspotKey();

  /**
   * <pre>
   *&#47; Amount awarded for dc transfer
   * </pre>
   *
   * <code>uint64 dc_transfer_reward = 2;</code>
   * @return The dcTransferReward.
   */
  long getDcTransferReward();

  /**
   * <pre>
   *&#47; count of rewardable bytes transfered
   * </pre>
   *
   * <code>uint64 rewardable_bytes = 3;</code>
   * @return The rewardableBytes.
   */
  long getRewardableBytes();

  /**
   * <pre>
   *&#47; Price of MOBILE &#64; 10^6 used when calculating rewards
   * </pre>
   *
   * <code>uint64 price = 4;</code>
   * @return The price.
   */
  long getPrice();
}
