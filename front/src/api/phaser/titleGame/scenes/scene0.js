import { Scene } from 'phaser';
import chaltteogi from '../assets/sprites/chaltteogi.png';

export default class scene0 extends Scene {
  constructor() {
    super({ key: 'scene0' });
  }

  preload() {
    this.load.image('chaltteogi', chaltteogi);
  }

  create() {
    this.scene.start('scene1');
  }
}
