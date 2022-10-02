import Phaser from 'phaser';
import BootScene from './scenes/BootScene';
import PlayScene from './scenes/PlayScene';

function createDropGame(containerId) {
  return new Phaser.Game({
    type: Phaser.AUTO,
    width: window.innerWidth,
    height: window.innerHeight,
    parent: containerId,
    physics: {
      default: 'arcade',
      arcade: {
        gravity: { y: 300 },
        debug: false,
      },
    },
    scene: [BootScene, PlayScene],
    fps: 30,
    render: { transparent: true },
  });
}

export { createDropGame };
