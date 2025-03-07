// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_lora.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc;

public interface iot_reward_shareOrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_lora.iot_reward_share)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * &#47; Unix timestamp in seconds of the start of the reward period
   * </pre>
   *
   * <code>uint64 start_period = 1;</code>
   * @return The startPeriod.
   */
  long getStartPeriod();

  /**
   * <pre>
   * &#47; Unix timestamp in seconds of the end of the reward period
   * </pre>
   *
   * <code>uint64 end_period = 2;</code>
   * @return The endPeriod.
   */
  long getEndPeriod();

  /**
   * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
   * @return Whether the gatewayReward field is set.
   */
  boolean hasGatewayReward();
  /**
   * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
   * @return The gatewayReward.
   */
  com.helium.grpc.gateway_reward getGatewayReward();
  /**
   * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
   */
  com.helium.grpc.gateway_rewardOrBuilder getGatewayRewardOrBuilder();

  /**
   * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
   * @return Whether the operationalReward field is set.
   */
  boolean hasOperationalReward();
  /**
   * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
   * @return The operationalReward.
   */
  com.helium.grpc.operational_reward getOperationalReward();
  /**
   * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
   */
  com.helium.grpc.operational_rewardOrBuilder getOperationalRewardOrBuilder();

  /**
   * <code>.helium.poc_lora.unallocated_reward unallocated_reward = 5;</code>
   * @return Whether the unallocatedReward field is set.
   */
  boolean hasUnallocatedReward();
  /**
   * <code>.helium.poc_lora.unallocated_reward unallocated_reward = 5;</code>
   * @return The unallocatedReward.
   */
  com.helium.grpc.unallocated_reward getUnallocatedReward();
  /**
   * <code>.helium.poc_lora.unallocated_reward unallocated_reward = 5;</code>
   */
  com.helium.grpc.unallocated_rewardOrBuilder getUnallocatedRewardOrBuilder();

  com.helium.grpc.iot_reward_share.RewardCase getRewardCase();
}
