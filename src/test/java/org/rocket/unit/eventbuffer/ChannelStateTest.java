package org.rocket.unit.eventbuffer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rocket.buffer.ChannelState;

@ExtendWith(MockitoExtension.class)
class ChannelStateTest {

  @Test
  void testChannelSetMessageNumberAndVerifyHigherAndLowerNumbers() {
    var channelState = new ChannelState();
    channelState.setMessageNumber("193271a9-c9cf-404a-8f83-838e71d9ae67", 1);

    var isHigerMessageNumber =
        channelState.isHigherMessageNumber("193271a9-c9cf-404a-8f83-838e71d9ae67", 2);
    var isLowerMessageNumber =
        channelState.isHigherMessageNumber("193271a9-c9cf-404a-8f83-838e71d9ae67", 1);
    var isEqualMessageNumberAndShouldBeDiscarded =
        channelState.isHigherMessageNumber("193271a9-c9cf-404a-8f83-838e71d9ae67", 1);

    assertTrue(isHigerMessageNumber);
    assertFalse(isLowerMessageNumber);
    assertFalse(isEqualMessageNumberAndShouldBeDiscarded);
  }
}
