import { Scene } from 'phaser';
import sky from '@/api/phaser/assets/sky.png';
import bomb from '@/api/phaser/assets/bomb.png';
import thudMp3 from '@/api/phaser/assets/thud.mp3';
import thudOgg from '@/api/phaser/assets/thud.ogg';

export default class BootScene extends Scene {
  constructor() {
    super({ key: 'BootScene' });
  }

  preload() {
    this.load.image('sky', sky);
    this.load.image('bomb', bomb);
    this.load.audio('thud', [thudMp3, thudOgg]);
  }

  create() {
    this.scene.start('PlayScene');
  }
}
