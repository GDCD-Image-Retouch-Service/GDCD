import { Scene } from 'phaser';
import chaltteogi from '../assets/sprites/chaltteogi.png';

export default class scene0 extends Scene {
  constructor() {
    super({ key: 'scene0' });
  }

  preload() {
    // this.load.setBaseURL('https://cdn.jsdelivr.net/gh/kefik/kenney/Shooter/');
    this.load.image('playership1', 'playerShip1_blue.png');
    this.load.image('chaltteogi', chaltteogi);
  }

  create() {
    this.scene.start('scene1');
  }
}
