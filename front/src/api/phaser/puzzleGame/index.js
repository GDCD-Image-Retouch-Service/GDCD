import Phaser from 'phaser';
import scene0 from './scenes/scene0';
import scene1 from './scenes/scene1';

function createPuzzleGame(containerId) {
  return new Phaser.Game({
    type: Phaser.AUTO,
    width: 300,
    height: 600,
    physics: {
      default: 'arcade',
      arcade: {
        gravity: { y: 300 },
        debug: false,
      },
    },
    parent: containerId,
    scene: [scene0, scene1],
    fps: 30,
    backgroundColor: '#18216D',
  });
}

export { createPuzzleGame };
