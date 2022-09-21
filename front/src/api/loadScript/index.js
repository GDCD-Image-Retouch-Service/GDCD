/**
 * SDK 등 외부 스크립트를 Load 하고 프로미스를 반환하는 함수 입니다.
 * @param string className 임포트하는 스크립트의 이름
 * @param string srcUrl 임포트할 주소
 * @return Promise
 */
const loadScript = (className, srcUrl) => {
  const promise = new Promise((resolve, reject) => {
    try {
      let script = document.createElement('script');
      script.setAttribute('class', className);
      script.setAttribute('src', srcUrl);
      document.head.appendChild(script);
      resolve('loadScript done');
    } catch {
      reject('loadScript failed');
    }
  });
  return promise;
};

export { loadScript };
