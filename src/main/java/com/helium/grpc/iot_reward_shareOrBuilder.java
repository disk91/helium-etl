// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/poc_lora.proto

package com.helium.grpc;

public interface iot_reward_shareOrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_lora.iot_reward_share)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *&#47; Unix timestamp in seconds of the start of the reward period
   * </pre>
   *
   * <code>uint64 start_period = 1;</code>
   * @return The startPeriod.
   */
  long getStartPeriod();

  /**
   * <pre>
   *&#47; Unix timestamp in seconds of the end of the reward period
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
  gateway_reward getGatewayReward();
  /**
   * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
   */
  gateway_rewardOrBuilder getGatewayRewardOrBuilder();

  /**
   * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
   * @return Whether the operationalReward field is set.
   */
  boolean hasOperationalReward();
  /**
   * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
   * @return The operationalReward.
   */
  operational_reward getOperationalReward();
  /**
   * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
   */
  operational_rewardOrBuilder getOperationalRewardOrBuilder();

  iot_reward_share.RewardCase getRewardCase();
}
